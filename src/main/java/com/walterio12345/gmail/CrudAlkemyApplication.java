package com.walterio12345.gmail;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.walterio12345.gmail.Repository.CharacterRepository;
import com.walterio12345.gmail.Repository.GenderRepository;
import com.walterio12345.gmail.Repository.MovieRepository;
import com.walterio12345.gmail.entity.Character;
import com.walterio12345.gmail.entity.Movie;

@SpringBootApplication
public class CrudAlkemyApplication {//implements CommandLineRunner

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private GenderRepository genderRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudAlkemyApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Character pepe=new Character();
//		pepe.setAge(36);
//		pepe.setImage(null);
//		pepe.setName("SPIDERMAN");
//		
//		Movie mmMovie=(movieRepository.findById("06f2d5e2-06ae-435d-ae09-844e7ad42167")).get();
//		Movie nneMovie=(movieRepository.findById("07507ef7-c20e-45b6-818a-265af04fd73b")).get();
//		List<Movie>movies=new ArrayList<>();
//		movies.add(mmMovie);
//		movies.add(nneMovie);
//		pepe.setMovies(movies);
//		characterRepository.save(pepe);
		//Character pipo=characterRepository.findById("72f72f0d-9c1d-4271-8e0b-8f1dd94e847e").get();
		
		//Movie movie=new Movie();
		//movie.getCharacters().add(pipo);
		//pipo.getMovies().add(movie);
//		movie.setTitle("La peli");
//		movie.setCreationDate(new Date());
//		movie.setGender(genderRepository.findById("83a4b0cf-3622-4246-bb54-ccdf4d6d2519").get());
//		movie.setImage(null);
//		movie.setQualification(3);
//		movie.setCharacters(characterRepository.findAll());
		//movieRepository.save(movie);
		//characterRepository.save(pipo);
		//Movie movie=movieRepository.findById("62979deb-4d1f-47e9-9003-cc1fc7dace1e").get();
		//movie.getCharacters().add(pipo);
		//movieRepository.save(movie);
		//pipo.setMovies(movieRepository.findAll());
		//characterRepository.save(pipo);
		//System.out.println("$$$$$--$$$$"+genderRepository.findOne("39558f31-7a4b-43fd-9267-b4ded7813f75"));
//	}

}
