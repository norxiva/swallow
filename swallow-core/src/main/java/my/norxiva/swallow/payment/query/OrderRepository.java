package my.norxiva.swallow.payment.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
