package com.id2real.gevisco.etudes.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnseignement is a Querydsl query type for Enseignement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEnseignement extends EntityPathBase<Enseignement> {

    private static final long serialVersionUID = 1397486047L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnseignement enseignement = new QEnseignement("enseignement");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final BooleanPath active = createBoolean("active");

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

    public final QMatiere matiere;

    public final QProfesseur professeur;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QEnseignement(String variable) {
        this(Enseignement.class, forVariable(variable), INITS);
    }

    public QEnseignement(Path<? extends Enseignement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnseignement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnseignement(PathMetadata metadata, PathInits inits) {
        this(Enseignement.class, metadata, inits);
    }

    public QEnseignement(Class<? extends Enseignement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classe = inits.isInitialized("classe") ? new QClasse(forProperty("classe"), inits.get("classe")) : null;
        this.matiere = inits.isInitialized("matiere") ? new QMatiere(forProperty("matiere")) : null;
        this.professeur = inits.isInitialized("professeur") ? new QProfesseur(forProperty("professeur")) : null;
    }

}

