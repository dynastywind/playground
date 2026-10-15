package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class SearchRequest {
    Long id;
    SourceRegionInfo sourceRegionInfo;
    List<SourceRegionInfo> sourceRegionInfos;
}
