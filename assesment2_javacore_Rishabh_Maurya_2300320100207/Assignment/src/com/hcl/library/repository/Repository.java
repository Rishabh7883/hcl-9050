package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.repository;


import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean deleteById(ID id);
}