package org.example;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Task {
    Long id;
    String conf;
}
