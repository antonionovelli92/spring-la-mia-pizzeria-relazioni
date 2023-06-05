package org.java.pizza.repo;



import org.java.pizza.pojo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
	
}


