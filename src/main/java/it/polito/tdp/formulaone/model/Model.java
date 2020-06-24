package it.polito.tdp.formulaone.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private FormulaOneDAO dao;
	private Map<Integer, Driver> drivers;
	private Graph<Driver, DefaultWeightedEdge> grafo;
	private List<Adiacenza> adiacenze;
	
	private List<Driver> bestDreamTeam;
	private int bestDreamTeamValue;
	
	public Model() {
		this.dao = new FormulaOneDAO();
		drivers = new HashMap<>();
		adiacenze = new ArrayList<>();
	}
	
	public List<Season> getSeasons(){
		return dao.getAllSeasons();
	}
	
	public void creaGrafo(Season sea) {
		this.drivers = dao.getPilotiByAnno(sea);
		this.grafo = new SimpleDirectedWeightedGraph<Driver, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, drivers.values());
	
		adiacenze = dao.getAdiacenze(sea, this.drivers);
		
		for(Adiacenza a : adiacenze) {
			Graphs.addEdge(this.grafo, a.getD1(), a.getD2(), a.getCount());
		}
		
		System.out.format("Grafo creato con %d vertici e %d archi\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Driver getMigliorPilota() {
		
		if(grafo==null) {
			new RuntimeException("Creare il grafo!");
		}
		
		Driver best = null;
		Integer differenza = 0;
		
		for(Driver d : this.grafo.vertexSet()) {
			Integer temp = 0;
			
			for(DefaultWeightedEdge e : grafo.outgoingEdgesOf(d)) {
				temp += (int)grafo.getEdgeWeight(e);
			}
			
			for(DefaultWeightedEdge e : grafo.incomingEdgesOf(d)) {
				temp -= (int)grafo.getEdgeWeight(e);
			}
			
			if(temp>differenza) {
				differenza = temp;
				best = d;
			}
		}
		
		if(best==null) {
			new RuntimeException("ERRORE.\n");
		}
		
		return best;
	}
	
	public List<Driver> getDreamTeam(int k){
		bestDreamTeam = new ArrayList<>();
		bestDreamTeamValue = Integer.MAX_VALUE;
		recursive(0, k, new ArrayList<Driver>());
		return bestDreamTeam;
	}

	private void recursive(int livello, int k, ArrayList<Driver> tempDreamTeam) {
		
		// condizione di terminazione
		if(livello>=k) {
			int valore = evaluate(tempDreamTeam);
			if(valore<bestDreamTeamValue) {
				bestDreamTeamValue = valore;
				bestDreamTeam = new ArrayList<>(tempDreamTeam);
				return;
			}
		}
		
		for(Driver d : this.grafo.vertexSet()) {
			if(!tempDreamTeam.contains(d)) {
				tempDreamTeam.add(d);
				recursive(livello+1, k, tempDreamTeam);
				tempDreamTeam.remove(d);
			}
		}
	}

	private int evaluate(ArrayList<Driver> tempDreamTeam) {
		int sum = 0;
		
		Set<Driver> in = new HashSet<>(tempDreamTeam);
		Set<Driver> out = new HashSet<>(grafo.vertexSet());
		out.removeAll(in);
		// OUT = tutti i vertici - dream team = vertici che non stanno nel dream team
		
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			if(out.contains(grafo.getEdgeSource(e)) && in.contains(grafo.getEdgeTarget(e))) {
				sum += grafo.getEdgeWeight(e);
			}
		}
		return sum;
	}
	
	
}
