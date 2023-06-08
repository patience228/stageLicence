package com.id2real.gevisco.etudes.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfesseur is a Querydsl query type for Professeur
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProfesseur extends EntityPathBase<Professeur> {

    private static final long serialVersionUID = 275461351L;

    public static final QProfesseur professeur = new QProfesseur("professeur");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath adresse = createString("adresse");

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

    public final NumberPath<Long> date_naissance = createNumber("date_naissance", Long.class);

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

    public final StringPath lieu_naissance = createString("lieu_naissance");

    public final StringPath nationalite = createString("nationalite");

    public final StringPath nom = createString("nom");

    public final StringPath prenom = createString("prenom");

    public final StringPath sexe = createString("sexe");

    public final StringPath telephone = createString("telephone");

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QProfesseur(String variable) {
        super(Professeur.class, forVariable(variable));
    }

    public QProfesseur(Path<? extends Professeur> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfesseur(PathMetadata metadata) {
        super(Professeur.class, metadata);
    }

}

