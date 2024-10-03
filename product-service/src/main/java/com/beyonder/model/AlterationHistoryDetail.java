package com.beyonder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "alteration_history_detail",indexes = @Index(name="alteration_history_detail_pk",columnList = "id",unique = true))
public class AlterationHistoryDetail extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "id",nullable = false,columnDefinition = "uuid")
    public String id;

    @ManyToOne
    @JoinColumn(name="alteration_history_id", nullable=false)
    public AlterationHistory alterationHistory;

    @Column(name = "code", length = 50,columnDefinition = "varchar(255)")
    public String code;

    @Column(name = "value", columnDefinition = "TEXT")
    public String value;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 50,columnDefinition = "varchar(255)")
    private String createBy;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 50,columnDefinition = "varchar(255)")
    private Date updatedBy;

}