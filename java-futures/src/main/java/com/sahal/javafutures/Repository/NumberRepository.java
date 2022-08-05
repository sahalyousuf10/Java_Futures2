package com.sahal.javafutures.Repository;

import com.sahal.javafutures.Model.NumberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends JpaRepository<NumberModel, Long> {
}
