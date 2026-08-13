package com.nana.repository;

import com.nana.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 基于内存的 {@link UserRepository} 实现，用于骨架演示。
 */
@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public List<User> findAll() {
        return store.values().stream()
                .sorted((a, b) -> Long.compare(a.id(), b.id()))
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public User save(User user) {
        Long id = user.id() == null ? sequence.incrementAndGet() : user.id();
        User saved = new User(id, user.name(), user.email());
        store.put(id, saved);
        return saved;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
