package com.id2real.gevisco.preinscription.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPreinscription is a Querydsl query type for Preinscription
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPreinscription extends EntityPathBase<Preinscription> {

    private static final long serialVersionUID = 1062873775L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPreinscription preinscription = new QPreinscription("preinscription");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final QAnnee_scolaire annee;

    public final StringPath bulletin1 = createString("bulletin1");

    public final StringPath bulletin2 = createString("bulletin2");

    public final StringPath bulletin3 = createString("bulletin3");

    public final StringPath classe_anterieure = createString("classe_anterieure");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.Instant> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdIpAdresse = _super.createdIpAdresse;

    //inherited
    public final StringPath createdUserAgent = _super.createdUserAgent;

    //inherited
    public final StringPath createdUserDevice = _super.createdUserDevice;

    //inherited
    public final StringPath createdZone = _super.createdZone;

    public final NumberPath<Long> date_preinscription = createNumber("date_preinscription", Long.class);

    public final StringPath ecole_provenance = createString("ecole_provenance");

    public final QEleve eleve;

    public final NumberPath<Integer> etat = createNumber("etat", Integer.class);

    public final NumberPath<Float> frais = createNumber("frais", Float.class);

    public final StringPath id = createString("id");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.Instant> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final StringPath lastModifiedIpAdresse = _super.lastModifiedIpAdresse;

    //inherited
    public final StringPath lastModifiedUserAgent = _super.lastModifiedUserAgent;

    //inherited
    public final StringPath lastModifiedUserDevice = _super.lastModifiedUserDevice;

    //inherited
    public final StringPath lastModifiedZone = _super.lastModifiedZone;

    public final NumberPath<Float> moyenne_obtenue = createNumber("moyenne_obtenue", Float.class);

    public final QNiveau niveau;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QPreinscription(String variable) {
        this(Preinscription.class, forVariable(variable), INITS);
    }

    public QPreinscription(Path<? extends Preinscription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPreinscription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPreinscription(PathMetadata metadata, PathInits inits) {
        this(Preinscription.class, metadata, inits);
    }

    public QPreinscription(Class<? extends Preinscription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.annee = inits.isInitialized("annee") ? new QAnnee_scolaire(forProperty("annee")) : null;
        this.eleve = inits.isInitialized("eleve") ? new QEleve(forProperty("eleve")) : null;
        this.niveau = inits.isInitialized("niveau") ? new QNiveau(forProperty("niveau")) : null;
    }

}

