package com.albertogiunta.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationRepository extends MongoRepository<Stations, String> {

    Stations findByNameLong(String longName);

    Stations findByNameShort(String shortName);

    Stations findByStationLongId(String longId);

    Stations findByStationShortId(String shortId);

}
