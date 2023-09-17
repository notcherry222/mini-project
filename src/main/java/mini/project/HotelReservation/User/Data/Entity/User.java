package mini.project.HotelReservation.User.Data.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mini.project.HotelReservation.AuditTime;
import mini.project.HotelReservation.Host.Data.Entity.Hotel;
import mini.project.HotelReservation.Host.Repository.HotelRepository;
import mini.project.HotelReservation.Reservation.Data.Entity.Reservation;
import mini.project.HotelReservation.enumerate.UserRole;
import mini.project.HotelReservation.enumerate.UserStatus;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User extends AuditTime {

    @Id @GeneratedValue
    private Long userId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "user")
    List<Reservation> reservations;

    @Builder
    public User(String name, String email, String password,
                String phoneNumber, UserStatus status, UserRole role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.role = role;
    }

    //유저가 호텔인 경우, 호텔 아이디를 조회해서 호텔 가져오기
    public void foreignHotel(Hotel hotel){
            this.hotel = hotel;
    }

    public void upDateInfo(User user){
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        phoneNumber = user.getPhoneNumber();
    }

    public void changeStatus(){
        this.status = UserStatus.USER_STATUS_ACTIVE;
    }

    public void deactive(){
        this.status = UserStatus.USER_STATUS_DEACTIVE;
    }

}