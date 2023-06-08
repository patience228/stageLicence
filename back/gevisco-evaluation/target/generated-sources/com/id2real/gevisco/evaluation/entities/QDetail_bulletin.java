package com.id2real.gevisco.evaluation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDetail_bulletin is a Querydsl query type for Detail_bulletin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDetail_bulletin extends EntityPathBase<Detail_bulletin> {

    private static final long serialVersionUID = -808496972L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDetail_bulletin detail_bulletin = new QDetail_bulletin("detail_bulletin");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final QBulletin bulletin;

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

    public final NumberPath<Float> moyenne = createNumber("moyenne", Float.class);

    public final QNote note;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QDetail_bulletin(String variable) {
        this(Detail_bulletin.class, forVariable(variable), INITS);
    }

    public QDetail_bulletin(Path<? extends Detail_bulletin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDetail_bulletin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDetail_bulletin(PathMetadata metadata, PathInits inits) {
        this(Detail_bulletin.class, metadata, inits);
    }

    public QDetail_bulletin(Class<? extends Detail_bulletin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bulletin = inits.isInitialized("bulletin") ? new QBulletin(forProperty("bulletin"), inits.get("bulletin")) : null;
        this.note = inits.isInitialized("note") ? new QNote(forProperty("note"), inits.get("note")) : null;
    }

}

