import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private baseUrl = 'https://meeting-room-manager.herokuapp.com/api/v1/rooms';

  constructor(private http : HttpClient) { }

  getRoomList() : Observable<any>{
    return this.http.get(`${this.baseUrl}`);
  }

  getRoom(id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createRoom(room : Object) : Observable<Object>{
    return this.http.post(`${this.baseUrl}`, room);
  }

  updateRoom(id : number, value : any) : Observable<Object>{
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteRoom(id : number) : Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType : 'text'});
  }

}
