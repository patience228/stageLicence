package com.id2real.gevisco.preinscription.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnnee_scolaire is a Querydsl query type for Annee_scolaire
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAnnee_scolaire extends EntityPathBase<Annee_scolaire> {

    private static final long serialVersionUID = 2107574176L;

    public static final QAnnee_scolaire annee_scolaire = new QAnnee_scolaire("annee_scolaire");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final BooleanPath active = createBoolean("active");

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

    public final NumberPath<Long> date_debut = createNumber("date_debut", Long.class);

    public final NumberPath<Long> date_fin = createNumber("date_fin", Long.class);

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

    public final StringPath libelle = createString("libelle");

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QAnnee_scolaire(String variable) {
        super(Annee_scolaire.class, forVariable(variable));
    }

    public QAnnee_scolaire(Path<? extends Annee_scolaire> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnnee_scolaire(PathMetadata metadata) {
        super(Annee_scolaire.class, metadata);
    }

}

