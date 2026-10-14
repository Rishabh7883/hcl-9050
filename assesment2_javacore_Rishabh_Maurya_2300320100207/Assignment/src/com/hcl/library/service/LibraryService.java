package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.service;

import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.exception.BorrowLimitExceededException;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.exception.ItemNotAvailableException;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.exception.ItemNotFoundException;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.exception.LibraryException;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model.Book;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model.LibraryItem;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model.Member;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.repository.InMemoryRepository;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.repository.Repository;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.util.SearchCache;

import java.io.*;
import java.util.*;

public class LibraryService {
    private final Repository<LibraryItem, Integer> itemRepository;
    private final Repository<Member, Integer> memberRepository;
    private final Map<Integer, Queue<Member>> waitlist = new HashMap<>();
    private final SearchCache searchCache = new SearchCache();
    private final Set<String> isbns = new HashSet<>();

    public LibraryService() {
        itemRepository = new InMemoryRepository<>(LibraryItem::getId);
        memberRepository = new InMemoryRepository<>(Member::getId);
    }

    public void addItem(LibraryItem item) {
        if (item instanceof Book) {
            Book book = (Book) item;

            if (!isbns.add(book.getIsbn())) {
                throw new LibraryException("ISBN already exists");
            }
        }

        itemRepository.save(item);
    }

    public void registerMember(Member member) {
        if (memberRepository.findById(member.getId()).isPresent()) {
            throw new LibraryException("Member ID already exists");
        }

        memberRepository.save(member);
    }

    public LibraryItem getItem(int id) {
        return itemRepository.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException("Item " + id + " not found"));
    }

    public Member getMember(int id) {
        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new LibraryException("Member " + id + " not found"));
    }

    public List<LibraryItem> getItems() {
        return itemRepository.findAll();
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public void borrowItem(int memberId, int itemId) {
        Member member = getMember(memberId);
        LibraryItem item = getItem(itemId);

        if (!item.isAvailable()) {
            throw new ItemNotAvailableException(
                    "Item " + itemId + " is not available");
        }

        if (member.getBorrowedCount() >= 3) {
            throw new BorrowLimitExceededException(
                    "A member can borrow a maximum of 3 items");
        }

        item.borrow();
        member.addBorrowedItem(item);
    }

    public double returnItem(int memberId, int itemId, int daysLate) {
        Member member = getMember(memberId);
        LibraryItem item = getItem(itemId);

        if (!member.getBorrowedItems().contains(item)) {
            throw new LibraryException(
                    "This member has not borrowed this item");
        }

        double fee = item.lateFee(daysLate);
        member.removeBorrowedItem(item);
        item.giveBack();

        Queue<Member> queue=waitlist.get(itemId);

        if (queue != null && !queue.isEmpty()) {
            Member nextMember=queue.peek();
            System.out.println("Notification: Member "
                    + nextMember.getId() + " (" +nextMember.getName()
                    + ") is next in the waitlist for " + itemId + ".");
        }

        return fee;
    }

    public void addToWaitlist(int memberId, int itemId) {
        Member member = getMember(memberId);
        LibraryItem item = getItem(itemId);

        if (item.isAvailable()) {
            throw new LibraryException(
                    "Item is available; no waitlist required");
        }

        Queue<Member> queue = waitlist.computeIfAbsent(
                itemId, key -> new ArrayDeque<>());

        if (!queue.contains(member)) {
            queue.offer(member);
        }
    }

    public void showWaitlist(int itemId) {
        Queue<Member> queue = waitlist.get(itemId);

        if (queue == null || queue.isEmpty()) {
            System.out.println("No members in waitlist");
            return;
        }

        for (Member member : queue) {
            System.out.println(member);
        }
    }

    public List<LibraryItem> search(String title) {
        String key = title.toLowerCase();

        List<LibraryItem> results =new ArrayList<>();

        for (LibraryItem item : itemRepository.findAll()) {
            if (item.getTitle().toLowerCase().contains(key)) {
                results.add(item);
            }
        }

        searchCache.put(key, results.toString());
        return results;
    }

    public void exportReport(String path) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(path))) {

            for (LibraryItem item : itemRepository.findAll()) {
                writer.write(item.toString());
                writer.newLine();
            }

            for (Member member : memberRepository.findAll()) {
                writer.write(member.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new LibraryException("Unable to export report", e);
        }
    }

    public void removeOldItems(int beforeYear) {
        Iterator<LibraryItem> iterator =
                itemRepository.findAll().iterator();

        while (iterator.hasNext()) {
            LibraryItem item = iterator.next();

            if (item.getYear() < beforeYear && item.isAvailable()) {
                itemRepository.deleteById(item.getId());

                if (item instanceof Book) {
                    isbns.remove(((Book) item).getIsbn());
                }
            }
        }
    }

    public void printItemStatistics() {
        Map<String, Integer> count = new HashMap<>();

        for (LibraryItem item : itemRepository.findAll()) {
            String type = item.getClass().getSimpleName();
            count.merge(type, 1, Integer::sum);
        }

        System.out.println(count);
    }
}
