package com.id2real.gevisco.etudes.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTitulaire is a Querydsl query type for Titulaire
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTitulaire extends EntityPathBase<Titulaire> {

    private static final long serialVersionUID = -160744320L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTitulaire titulaire = new QTitulaire("titulaire");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final com.id2real.gevisco.preinscription.entities.QAnnee_scolaire annee;

    public final QClasse classe;

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

    public final QProfesseur professeur;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QTitulaire(String variable) {
        this(Titulaire.class, forVariable(variable), INITS);
    }

    public QTitulaire(Path<? extends Titulaire> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTitulaire(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTitulaire(PathMetadata metadata, PathInits inits) {
        this(Titulaire.class, metadata, inits);
    }

    public QTitulaire(Class<? extends Titulaire> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.annee = inits.isInitialized("annee") ? new com.id2real.gevisco.preinscription.entities.QAnnee_scolaire(forProperty("annee")) : null;
        this.classe = inits.isInitialized("classe") ? new QClasse(forProperty("classe"), inits.get("classe")) : null;
        this.professeur = inits.isInitialized("professeur") ? new QProfesseur(forProperty("professeur")) : null;
    }

}

