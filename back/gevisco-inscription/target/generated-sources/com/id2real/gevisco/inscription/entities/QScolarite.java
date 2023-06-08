package com.id2real.gevisco.inscription.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScolarite is a Querydsl query type for Scolarite
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScolarite extends EntityPathBase<Scolarite> {

    private static final long serialVersionUID = -498230901L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScolarite scolarite = new QScolarite("scolarite");

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

    public final NumberPath<Float> montant_scolarite = createNumber("montant_scolarite", Float.class);

    public final com.id2real.gevisco.preinscription.entities.QNiveau niveau;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QScolarite(String variable) {
        this(Scolarite.class, forVariable(variable), INITS);
    }

    public QScolarite(Path<? extends Scolarite> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScolarite(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScolarite(PathMetadata metadata, PathInits inits) {
        this(Scolarite.class, metadata, inits);
    }

    public QScolarite(Class<? extends Scolarite> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.annee = inits.isInitialized("annee") ? new com.id2real.gevisco.preinscription.entities.QAnnee_scolaire(forProperty("annee")) : null;
        this.niveau = inits.isInitialized("niveau") ? new com.id2real.gevisco.preinscription.entities.QNiveau(forProperty("niveau")) : null;
    }

}

