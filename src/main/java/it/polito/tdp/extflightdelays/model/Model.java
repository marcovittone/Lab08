package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	private Map <Integer,Airport> mappa = new HashMap<>();
	
	
	public Model() {
		
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.dao = new ExtFlightDelaysDAO();
	}
	
	
	public void creaGrafo(int minimo) {
		
		this.dao.loadAllAirports(this.mappa);
		Graphs.addAllVertices(this.grafo,this.mappa.values());
		
		List<Rotta> rotta = this.dao.loadAverageFlights(mappa);
		
		
		for(Rotta r: rotta) {
			
			DefaultWeightedEdge e = this.grafo.getEdge(r.getA1(), r.getA2());
			
			if(e==null)
				Graphs.addEdge(this.grafo, r.getA1(), r.getA2(), r.getAverageDistance());
			else {
				
				double peso = this.grafo.getEdgeWeight(e);
				double nuovoPeso = ( peso+ r.getAverageDistance())/ 2;
				this.grafo.setEdgeWeight(e, nuovoPeso);
			}
				
		}
		
		Set<DefaultWeightedEdge> set= new HashSet(this.grafo.edgeSet());
		
		for(DefaultWeightedEdge de: set)
		{
			if(this.grafo.getEdgeWeight(de) < minimo)
				this.grafo.removeEdge(de);
		}
		
		System.out.println("GRAFO CREATO CON :");
		System.out.println(this.grafo.vertexSet().size()+" vertici");
		System.out.println("e "+this.grafo.edgeSet().size()+" archi");
		
		
		
		
		
	}

}
