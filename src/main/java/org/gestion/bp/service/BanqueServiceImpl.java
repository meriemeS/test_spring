package org.gestion.bp.service;

import java.sql.Date;
import java.util.List;

import org.gestion.bp.dao.IBanqueDao;
import org.gestion.bp.entities.Client;
import org.gestion.bp.entities.Compte;
import org.gestion.bp.entities.Employe;
import org.gestion.bp.entities.Groupe;
import org.gestion.bp.entities.Operation;
import org.gestion.bp.entities.Retrait;
import org.gestion.bp.entities.Versement;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class BanqueServiceImpl implements IbanqueService{
	private IBanqueDao dao;
	

	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public void verser(double mt,String codeCompte,Long codeEmp) {
		dao.addOperation(new Versement(new Date(100), mt), codeCompte, codeEmp);
		Compte cp = dao.consulterCompte(codeCompte);
		cp.setSolde(cp.getSolde()+mt);
	}

	@Override
	public void retirer(double mt,String codeCompte,Long codeEmp){
		dao.addOperation(new Retrait(new Date(100), mt), codeCompte, codeEmp);
		Compte cp = dao.consulterCompte(codeCompte);
		cp.setSolde(cp.getSolde()- mt);
	}

	@Override
	public void virement(String codeCompte1,String codeCompte2,double mt,Long codeEmp){
		retirer(mt, codeCompte1, codeEmp);
		verser(mt, codeCompte2, codeEmp);
	}


	@Override
	public Client addclient(Client c) {
		return dao.addclient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		return dao.addGroupe(g);
	}

	@Override
	public void addEmpToGroupe(Long codeGr, Long codeEmp) {
		dao.addEmpToGroupe(codeGr, codeEmp);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.addCompte(cp, codeCli, codeEmp);
	}

	@Override
	public Operation addOperation(Operation o, String codeCompte, Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.addOperation(o, codeCompte, codeEmp);
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		return dao.consulterCompte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperations(String codeCpte) {
		// TODO Auto-generated method stub
		return dao.consulterOperations(codeCpte);
	}

	@Override
	public Client consulterClient(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.consulterClient(codeCli);
	}

	@Override
	public List<Client> consulterClients(String mc) {
		// TODO Auto-generated method stub
		return dao.consulterClients(mc);
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.getComptesByClient(codeCli);
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.getComptesByEmploye(codeEmp);
	}

	@Override
	public List<Employe> getEmployes() {
		// TODO Auto-generated method stub
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() {
		// TODO Auto-generated method stub
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		// TODO Auto-generated method stub
		return dao.getEmployesByGroupe(codeGr);
	}

}
