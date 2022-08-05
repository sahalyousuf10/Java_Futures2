package com.sahal.javafutures.ValueObject;

import com.sahal.javafutures.Model.NumberModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueObject {
    private CompletableFuture<NumberModel> numberModel;
    private boolean saveInDatabase;
}
