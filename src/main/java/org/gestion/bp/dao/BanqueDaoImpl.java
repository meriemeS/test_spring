package org.gestion.bp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gestion.bp.entities.Client;
import org.gestion.bp.entities.Compte;
import org.gestion.bp.entities.Employe;
import org.gestion.bp.entities.Groupe;
import org.gestion.bp.entities.Operation;

public class BanqueDaoImpl implements IBanqueDao{
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Client addclient(Client c) {
		em.persist(c);
		return c ;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		if (codeSup!=null){
			Employe sup = em.find(Employe.class, codeSup);
			e.setEmployeSup(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmpToGroupe(Long codeGr, Long codeEmp) {
		Employe e=em.find(Employe.class, codeEmp);
		Groupe g = em.find(Groupe.class, codeGr);
		e.getGroupes().add(g);
		g.getEmployes().add(e);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		Client cli=em.find(Client.class, codeCli);
		Employe emp = em.find(Employe.class, codeEmp);
		cp.setClient(cli);
		cp.setEmploye(emp);
		em.persist(cp);
		return cp;
	}

	@Override
	public Operation addOperation(Operation o, String codeCompte, Long codeEmp) {
		Compte cp=em.find(Compte.class, codeCompte);
		Employe emp = em.find(Employe.class, codeEmp);
		o.setCompte(cp);;
		o.setEmploye(emp);
		em.persist(o);
		return o;
	}


	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp = em.find(Compte.class,codeCpte);
		if(cp==null) throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public List<Operation> consulterOperations(String codeCpte) {
		Query req = em.createQuery("select o from Operation o where o.compte.codeCompte=:x");
		req.setParameter("x", codeCpte);
		return req.getResultList();
	}

	@Override
	public Client consulterClient(Long codeCli) {
		Client cli = em.find(Client.class,codeCli);
		if(cli==null) throw new RuntimeException("Client introuvable");
		return cli;
	}

	@Override
	public List<Client> consulterClients(String mc) {
		Query req = em.createQuery("select c from Client c where c.nomClient like :x");
		req.setParameter("x", "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		Query req = em.createQuery("select cp from Compte cp where cp.client.codeClient=:x");
		req.setParameter("x", codeCli);
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		Query req = em.createQuery("select cp from Compte cp where cp.employe.codeEmploye=:x");
		req.setParameter("x", codeEmp);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		Query req = em.createQuery("select * from Employe");
		return req.getResultList();
	}

	@Override
	public List<Groupe> getGroupes() {
		Query req = em.createQuery("select e from Groupe e");
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		Query req = em.createQuery("select emp from Employe emp where emp.groupes.codeGroupe=:x");
		req.setParameter("x", codeGr);
		return req.getResultList();
	}

}
