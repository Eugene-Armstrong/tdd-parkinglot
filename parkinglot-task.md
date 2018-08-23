1. ParkingLotServiceTest - add parking-lot
    given new parking-lot
    when call ParkingLotService.addNewParkingLot()
    then return a new parkingLot

2. ParkingLotControllerTest - add parking lot
    given new parking-lot
    when post("/parkinglots")
    then return status code is 201

3. ParkingLotServiceTest - find all parking-lots
    given
    when call ParkingLotService.findAllParkingLot()
    then return list of parkingLots