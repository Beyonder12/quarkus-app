package com.beyonder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "alteration_history",indexes = @Index(name="alteration_history_pk",columnList = "id",unique = true))
public class AlterationHistory extends PanacheEntityBase implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private String id;

    @OneToMany(mappedBy="alteration_history")
    private List<AlterationHistoryDetail> alterationHistoryDetails;

    @Column(name = "policy_id", length = 36,columnDefinition = "varchar(36)")
    private String policyId;

    @Column(name = "reason_code", length = 50,columnDefinition = "varchar(50)")
    private String reasonCode;

    @Column(name = "status",length = 50,columnDefinition = "varchar(50)")
    private String status;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 50,columnDefinition = "varchar(50)")
    private String createBy;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 50,columnDefinition = "varchar(255)")
    private Date updatedBy;

}