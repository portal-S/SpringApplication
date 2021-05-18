package com.portal.springrest.repository.interfaces;

import com.portal.springrest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
