package it.polito.tdp.formulaone.db;

import java.util.List;
import java.util.Map;

import it.polito.tdp.formulaone.model.Circuit;
import it.polito.tdp.formulaone.model.Constructor;
import it.polito.tdp.formulaone.model.Driver;
import it.polito.tdp.formulaone.model.Season;

public class TestDAO {


	public static void main(String[] args) {
		FormulaOneDAO dao = new FormulaOneDAO() ;
		
		List<Season> seasons = dao.getAllSeasons() ;
		System.out.println(seasons);
		
		
		
	}
}
