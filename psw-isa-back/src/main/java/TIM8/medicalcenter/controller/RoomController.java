package TIM8.medicalcenter.controller;

import TIM8.medicalcenter.dto.FindRoomDTORequest;
import TIM8.medicalcenter.dto.FindRoomDTOResponse;
import TIM8.medicalcenter.dto.RoomDTO;
import TIM8.medicalcenter.model.Appointment;
import TIM8.medicalcenter.model.Room;
import TIM8.medicalcenter.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Funkcija kojom administrator nakon odabira pregleda za koji zeli da izvrsi pravljenje pregleda,dobija sve termine
     * koji su slobodni za odabrani termin
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/findRooms",method = RequestMethod.POST)
    public ResponseEntity<?> findRooms(@RequestBody FindRoomDTORequest request){
        List<Room> rooms = roomService.findByNameAndNumber(request.getName(),request.getNumber());
        List<FindRoomDTOResponse> res = new ArrayList<>();
        for(Room r : rooms){
            ArrayList<Date> appointments = new ArrayList<>();
            String dat = request.getDate();
            int year = Integer.parseInt(dat.split("-")[0]);
            int month = Integer.parseInt(dat.split("-")[1]);
            int day = Integer.parseInt(dat.split("-")[2]);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month-1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            //Date d = cal.getTime();

            for(int i = 0; i < 10; i++){
                //Date dd = new Date(d.getYear(),d.getMonth(),d.getDay(),8+i,0,0);
                Date dd = cal.getTime();
                dd.setHours(8+i);
                dd.setMinutes(0);
                dd.setSeconds(0);
                int ddy = dd.getYear();
                int ddm = dd.getMonth();
                int ddd = dd.getDay();
                int ddh = dd.getHours();
                int flag = 0;
                Set<Appointment> appointments1 = r.getAppointments();
                for(Appointment a : appointments1){
                    int ay = a.getDate().getYear();
                    int am = a.getDate().getMonth();
                    int ad = a.getDate().getDay();
                    int ah = a.getDate().getHours();
                    if(ay == ddy && am == ddm && ad == ddd && ah == ddh){
                        flag = 1;
                    }
                }
                if(flag == 0){
                    appointments.add(dd);
                }
            }
            res.add(new FindRoomDTOResponse(r.getId(), r.getName(),r.getNumber(),appointments));
        }
        return new ResponseEntity<>(res,HttpStatus.OK);

    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ResponseEntity<?> findAllRooms() {
        List<Room> rooms = roomService.findAll();
        List<FindRoomDTOResponse> response = new ArrayList<>();
        for(Room r : rooms){
            response.add(new FindRoomDTOResponse(r.getId(),r.getName(),r.getNumber(),null));
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    /**
     * Funkcija kojom administrator dobija sobe koje moze da menja
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getAdminRooms",method = RequestMethod.POST)
    public ResponseEntity<?> getAdminRooms(@RequestBody Id id) {
        List<Room> rooms = roomService.findAdminRooms(id.id);
        List<FindRoomDTOResponse> response = new ArrayList<>();
        for(Room r : rooms){
            response.add(new FindRoomDTOResponse(r.getId(),r.getName(),r.getNumber(),null));
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    /**
     * Funkcija kojom administrator vrsi menjanje sobe
     * @param room
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateRoom(@RequestBody RoomDTO room) {
        int a = roomService.updateRoom(room.getName(), room.getNumber(), room.getId());

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    /**
     * Funkcija kojom administrator kreira novu sobu
     * @param room
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO room) {
        Room r = roomService.save(room);

        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }


    static class Id {
        public Long id;
    }
}
