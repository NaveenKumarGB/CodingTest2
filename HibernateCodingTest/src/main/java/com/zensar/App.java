package com.zensar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.zensar.bean.Address;
import com.zensar.bean.BankAccount;
import com.zensar.bean.Employee;
import com.zensar.bean.Skill;

public class App {

	public static void loadTesting() {
		// write code here to load emp object

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
		EntityManager em = emf.createEntityManager();

		Employee employee = null;
		employee = em.find(Employee.class, 1); // session.load
		System.out.println(employee);

		em.close();
		emf.close();
	}

	public static void insideTesting() {
		// write code here to persist employee object

		List<Skill> skill = new ArrayList<>();
		skill.add(new Skill("java", 1));

		BankAccount bankAccount = new BankAccount(1, "SBIN090654");

		Address address = new Address(1, "banglore", "560064", null);

		Employee employee = new Employee("naveen", 33300.00, address, skill, bankAccount);

		employee.setAddress(address);
		address.setEmployee(employee);


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("data saved");
	}

	public static void main(String[] args) {
		//insideTesting();

		loadTesting();
	}
}
