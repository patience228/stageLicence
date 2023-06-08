package com.id2real.gevisco.preinscription.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEleve is a Querydsl query type for Eleve
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEleve extends EntityPathBase<Eleve> {

    private static final long serialVersionUID = -1504918221L;

    public static final QEleve eleve = new QEleve("eleve");

    public final com.id2real.socle.utils.audit.QAbstractAuditingEntity _super = new com.id2real.socle.utils.audit.QAbstractAuditingEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath adresse = createString("adresse");

    public final StringPath adresse_parent = createString("adresse_parent");

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

    public final StringPath id = createString("id");

    public final StringPath image = createString("image");

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

    public final StringPath matricule = createString("matricule");

    public final StringPath nationalite = createString("nationalite");

    public final StringPath nom = createString("nom");

    public final StringPath nom_parent = createString("nom_parent");

    public final StringPath prenom = createString("prenom");

    public final StringPath prenoms_parent = createString("prenoms_parent");

    public final StringPath profession_parent = createString("profession_parent");

    public final StringPath sexe = createString("sexe");

    public final StringPath telephone_parent = createString("telephone_parent");

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QEleve(String variable) {
        super(Eleve.class, forVariable(variable));
    }

    public QEleve(Path<? extends Eleve> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEleve(PathMetadata metadata) {
        super(Eleve.class, metadata);
    }

}

