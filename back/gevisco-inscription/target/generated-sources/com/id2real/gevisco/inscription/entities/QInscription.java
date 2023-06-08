package com.id2real.gevisco.inscription.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInscription is a Querydsl query type for Inscription
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInscription extends EntityPathBase<Inscription> {

    private static final long serialVersionUID = 1375633127L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInscription inscription = new QInscription("inscription");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final com.id2real.gevisco.preinscription.entities.QAnnee_scolaire annee;

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

    public final NumberPath<Long> date_inscription = createNumber("date_inscription", Long.class);

    public final StringPath derniere_classe = createString("derniere_classe");

    public final com.id2real.gevisco.preinscription.entities.QEleve eleve;

    public final NumberPath<Integer> etat = createNumber("etat", Integer.class);

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

    public final NumberPath<Float> montant_verse = createNumber("montant_verse", Float.class);

    public final com.id2real.gevisco.preinscription.entities.QNiveau niveau;

    public final com.id2real.gevisco.preinscription.entities.QPreinscription preinscription;

    public final NumberPath<Float> reste_payer = createNumber("reste_payer", Float.class);

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QInscription(String variable) {
        this(Inscription.class, forVariable(variable), INITS);
    }

    public QInscription(Path<? extends Inscription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInscription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInscription(PathMetadata metadata, PathInits inits) {
        this(Inscription.class, metadata, inits);
    }

    public QInscription(Class<? extends Inscription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.annee = inits.isInitialized("annee") ? new com.id2real.gevisco.preinscription.entities.QAnnee_scolaire(forProperty("annee")) : null;
        this.eleve = inits.isInitialized("eleve") ? new com.id2real.gevisco.preinscription.entities.QEleve(forProperty("eleve")) : null;
        this.niveau = inits.isInitialized("niveau") ? new com.id2real.gevisco.preinscription.entities.QNiveau(forProperty("niveau")) : null;
        this.preinscription = inits.isInitialized("preinscription") ? new com.id2real.gevisco.preinscription.entities.QPreinscription(forProperty("preinscription"), inits.get("preinscription")) : null;
    }

}

