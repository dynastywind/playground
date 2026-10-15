package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SourceRegionInfo {
    String geo;
    YarnQueue yarnQueue;
}
