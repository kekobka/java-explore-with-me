package ru.practicum.dto.mainservice.mapper;

import org.mapstruct.*;
import ru.practicum.dto.mainservice.dto.event.*;
import ru.practicum.dto.mainservice.model.Event;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {

    @Mapping(target = "category", ignore = true)
    Event mapToEvent(RequestEventDto requestEventDto);

    EventFullDto mapToEventFullDto(Event event, long viewsCount, long commentsCount);

    EventShortDto mapToShortEventDto(Event event, long viewsCount, long commentsCount);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "state", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEvent(UpdateEventUserRequest requestDto, @MappingTarget Event event);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "state", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEventAdminRequest(UpdateEventAdminRequest request, @MappingTarget Event event);

    @AfterMapping
    default void addCommentsAndViewsToEventDto(@MappingTarget EventFullDto dto, long viewsCount, long commentsCount) {
        dto.setCommentsCount(commentsCount);
        dto.setViews(viewsCount);
    }

    @AfterMapping
    default void setViews(@MappingTarget EventShortDto dto, long viewsCount, long commentsCount) {
        dto.setCommentsCount(commentsCount);
        dto.setViews(viewsCount);
    }
}