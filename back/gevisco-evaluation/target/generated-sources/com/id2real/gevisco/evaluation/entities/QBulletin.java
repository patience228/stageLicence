package com.id2real.gevisco.evaluation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBulletin is a Querydsl query type for Bulletin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBulletin extends EntityPathBase<Bulletin> {

    private static final long serialVersionUID = 1903293352L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBulletin bulletin = new QBulletin("bulletin");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

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

    public final com.id2real.gevisco.preinscription.entities.QEleve eleve;

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

    public QBulletin(String variable) {
        this(Bulletin.class, forVariable(variable), INITS);
    }

    public QBulletin(Path<? extends Bulletin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBulletin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBulletin(PathMetadata metadata, PathInits inits) {
        this(Bulletin.class, metadata, inits);
    }

    public QBulletin(Class<? extends Bulletin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eleve = inits.isInitialized("eleve") ? new com.id2real.gevisco.preinscription.entities.QEleve(forProperty("eleve")) : null;
    }

}

