package ru.neirostorm.util;

import org.springframework.data.jpa.domain.Specification;
import ru.neirostorm.model.Practice;
import ru.neirostorm.model.Topic;
import ru.neirostorm.model.Practice;

public class PracticeSpecifications {
    public static Specification<Practice> title(String keyword) {
        return (practice, cq, cb) -> {
            if (keyword == null || keyword.isEmpty()) {
                return cb.conjunction(); // Возвращает "пустое" условие, если ключевое слово пустое
            }
            String keywordPattern = "%" + keyword.toLowerCase() + "%";
            return cb.like(cb.lower(practice.get("title")), keywordPattern);
        };
    }

    public static Specification<Practice> topicIs(String topic) {
        return (root, query, criteriaBuilder) -> {
            if (topic == null || topic.isEmpty()) {
                return criteriaBuilder.conjunction(); // Вернуть "истина" (без фильтрации), если тема не выбрана
            }
            return criteriaBuilder.equal(root.get("topic"), Topic.fromDisplayName(topic).toString());
        };
    }
}
