package com.id2real.gevisco.etudes.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClasse is a Querydsl query type for Classe
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClasse extends EntityPathBase<Classe> {

    private static final long serialVersionUID = -1623152482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClasse classe = new QClasse("classe");

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

    public final com.id2real.gevisco.preinscription.entities.QNiveau niveau;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QClasse(String variable) {
        this(Classe.class, forVariable(variable), INITS);
    }

    public QClasse(Path<? extends Classe> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClasse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClasse(PathMetadata metadata, PathInits inits) {
        this(Classe.class, metadata, inits);
    }

    public QClasse(Class<? extends Classe> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.niveau = inits.isInitialized("niveau") ? new com.id2real.gevisco.preinscription.entities.QNiveau(forProperty("niveau")) : null;
    }

}

