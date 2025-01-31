package umc.TripPiece.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.TripPiece.domain.Map;

import java.util.List;
import java.util.Optional;

public interface MapRepository extends JpaRepository<Map, Long> {

    // 유저 ID로 맵을 조회 (updatedAt 최신순, NULL이면 createdAt 기준 정렬)
    @Query("""
            SELECT m FROM Map m 
            WHERE m.userId = :userId 
            ORDER BY 
                CASE WHEN m.updatedAt IS NULL THEN m.createdAt ELSE m.updatedAt END DESC
            """)
    List<Map> findByUserIdOrderedByUpdatedAt(Long userId);

    // 유저가 방문한 나라 수 조회
    @Query("SELECT COUNT(DISTINCT m.countryCode) FROM Map m WHERE m.userId = :userId")
    long countDistinctCountryCodeByUserId(Long userId);

    // 유저가 방문한 나라 리스트 조회
    @Query("SELECT DISTINCT m.countryCode FROM Map m WHERE m.userId = :userId")
    List<String> findDistinctCountryCodesByUserId(Long userId);

    // 유저가 방문한 도시 리스트 조회
    @Query("SELECT DISTINCT m.city.id FROM Map m WHERE m.userId = :userId")
    List<Long> findDistinctCityIdsByUserId(Long userId);

    // 유저가 방문한 도시 수 조회
    @Query("SELECT COUNT(DISTINCT m.city.id) FROM Map m WHERE m.userId = :userId")
    long countDistinctCityByUserId(Long userId);

    // 특정 유저가 특정 도시를 방문한 기록 찾기
    @Query("SELECT m FROM Map m WHERE m.userId = :userId AND m.countryCode = :countryCode AND m.city.id = :cityId")
    Optional<Map> findByUserIdAndCountryCodeAndCityId(Long userId, String countryCode, Long cityId);

    // 특정 유저가 특정 도시를 방문한 모든 기록 찾기
    @Query("SELECT m FROM Map m WHERE m.userId = :userId AND m.countryCode = :countryCode AND m.city.id = :cityId")
    List<Map> findAllByUserIdAndCountryCodeAndCityId(Long userId, String countryCode, Long cityId);

    // 특정 국가에서 유저가 색칠한 도시를 정렬된 순서로 조회 (updatedAt 최신, NULL이면 createdAt 기준 정렬)
    @Query("""
            SELECT m FROM Map m 
            WHERE m.countryCode = :countryCode AND m.userId = :userId 
            ORDER BY 
                CASE WHEN m.updatedAt IS NULL THEN m.createdAt ELSE m.updatedAt END DESC
            """)
    List<Map> findByCountryCodeAndUserIdOrderedByUpdatedAt(String countryCode, Long userId);
}
