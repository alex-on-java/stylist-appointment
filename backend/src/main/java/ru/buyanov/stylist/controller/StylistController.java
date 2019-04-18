package ru.buyanov.stylist.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.buyanov.stylist.model.Stylist;

@RepositoryRestResource(collectionResourceRel = "stylists", path = "stylists")
public interface StylistController extends CrudRepository<Stylist, Integer> {

}