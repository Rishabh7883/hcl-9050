package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.repository;

import java.util.*;
import java.util.function.Function;

public class InMemoryRepository<T, ID> implements Repository<T, ID> {
    private final Map<ID, T> data = new HashMap<>();
    private final Function<T, ID> idExtractor;

    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T entity) {
        data.put(idExtractor.apply(entity), entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public boolean deleteById(ID id) {
        return data.remove(id) != null;
    }
}