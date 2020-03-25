package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.Cotation;
import com.openclassrooms.escalade.entities.QTopo;
import com.openclassrooms.escalade.model.TopoSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class TopoPredicateBuilder {

    public static final QTopo topo = QTopo.topo;

    public static Predicate buildSearch(TopoSearch topoSearch) {
        return new BooleanBuilder()
                .and(isCountry(topoSearch.getCountry()))
                .and(isName(topoSearch.getName()))
                .and(isAvailable(topoSearch.isAvailable()))
                .and(isCotationMin(topoSearch.getCotationMin()))
                .and(isCotationMax(topoSearch.getCotationMax()));
    }

    private static BooleanExpression isCountry(String country) {
        return country != null ? topo.country.eq(country) : null;
    }

    private static BooleanExpression isName(String name) {
        return name != null ? topo.name.eq(name) : null;
    }

    private static BooleanExpression isAvailable(boolean isAvailable){
        return isAvailable ? topo.available.eq(isAvailable) : null;
    }

    private static BooleanExpression isCotationMin(Cotation cotationMin){
        return cotationMin != null ? topo.cotationMin.eq(cotationMin) : null;
    }

    private static BooleanExpression isCotationMax(Cotation cotationMax){
        return cotationMax != null ? topo.cotationMax.eq(cotationMax) : null;
    }
}
