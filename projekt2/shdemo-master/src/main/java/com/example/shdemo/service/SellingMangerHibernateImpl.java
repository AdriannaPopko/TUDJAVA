package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Owner;
import com.example.shdemo.domain.Pet;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void deleteOwner(Owner owner) {
		sessionFactory.getCurrentSession().delete(owner);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Owner> getAllOwners() {
		return sessionFactory.getCurrentSession().getNamedQuery("owner.all").list();
	}
	@Override
	public void updateOwner(Owner owner) {
		sessionFactory.getCurrentSession().merge(owner);
	}
	@Override
	public Owner findOwnerById(Owner owner) {
		return (Owner) sessionFactory.getCurrentSession().get(Owner.class, owner.getId());
	}
	@Override
	public void addOwner(Owner owner) {
		sessionFactory.getCurrentSession().persist(owner);
	}
	@Override
	public Owner findOwnerBylastName(String lastName) {
		return (Owner) sessionFactory.getCurrentSession().getNamedQuery("owner.bylastName").setString("lastName",lastName).list();
	}
	
	
	
	
	@Override
	public void addNewPet(Pet pet) {
		sessionFactory.getCurrentSession().persist(pet);	
	}
	@Override
	public Pet findPetById(Pet pet) {
		return (Pet) sessionFactory.getCurrentSession().get(Pet.class, pet.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pet> findPetBySpecies(String species) {
		return sessionFactory.getCurrentSession().getNamedQuery("pet.species").setString("species", species).list();
	}
	@Override
	public void updatePet(Pet pet) {
		sessionFactory.getCurrentSession().merge(pet);	
		
	}
	
	public void deletePet(Pet pet) {
		sessionFactory.getCurrentSession().delete(pet);
		//owner.setPets(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pet> getAllPets() {
		return sessionFactory.getCurrentSession().getNamedQuery("pet.all").list();
	}

	@Override
	public List<Pet> findOwnerByPet(Owner owner) {
		Owner o = (Owner) sessionFactory.getCurrentSession().get(Owner.class, owner.getId());
		return o.getPets();
	}
	
	@Override
	public void addPetToOwner(Pet pet, Owner owner) {
		Owner o = (Owner) sessionFactory.getCurrentSession().get(Owner.class, owner.getId());
		o.getPets().add(pet);
	}
}