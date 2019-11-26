package com.local.elastic.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.local.elastic.search.model.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
}