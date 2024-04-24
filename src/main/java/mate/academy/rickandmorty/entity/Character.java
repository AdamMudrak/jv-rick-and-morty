package mate.academy.rickandmorty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Character {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  Long externalId;
  String name;
  String status;
  String gender;
}
