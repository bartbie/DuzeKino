package org.duze.duzekino.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duze.duzekino.repository.TheaterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public  final class TheaterService {
    final TheaterRepository theaterRepository;

    //read movies and showings
}
