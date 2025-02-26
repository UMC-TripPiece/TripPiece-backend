package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.TripPiece.domain.common.BaseEntity;
import umc.TripPiece.domain.enums.Category;
//import umc.TripPiece.domain.enums.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TripPiece extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMO'")
    private Category category;

    @Setter
    @Column
    private String description;

    @Column
    @ColumnDefault("true")
    private Boolean travel_contain;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @OneToMany(mappedBy = "tripPiece", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "tripPiece", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "tripPiece", cascade = CascadeType.ALL)
    private List<Emoji> emojis = new ArrayList<>();

}
