package my.norxiva.swallow.merchant.query;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantSecretRepository extends MongoRepository<MerchantSecret, Long> {
}
