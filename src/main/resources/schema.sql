create table task (
    id bigint primary key,
    conf varchar(512)
);

--insert into task values(1, "{\"configuration\": {\"sourceRegionInfos\": [{\"geo\": \"US-TTP\", \"yarnQueue\": {\"queue\": \"queue\"}}]}}");