package ooga.model.entities;

import ooga.controller.JSONInformationDecoder;
import ooga.model.Model;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionChartGetter;
import ooga.model.collisions.collisionhandling.DefaultCollisionChartGetter;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.containers.exceptions.InvalidTypeException;
import ooga.model.entities.deadmovingentities.MovementQueue;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.info.ImmutableInfo;
import ooga.model.entities.info.Info;
import ooga.model.entities.livingentities.BasicStaticCharacter;
import ooga.model.entities.livingentities.movingentities.AutomaticMovingCharacter;

import java.lang.reflect.InvocationTargetException;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacter;
import ooga.model.entities.modelcallers.GameEnderCollidableEntity;

/**
 * Class that makes Entities of certain types to be added to containers.
 * Uses reflection to get from 'type' String to an Entity object
 */
public class EntityFactory {
    private JSONInformationDecoder decoder;

    public EntityFactory(JSONInformationDecoder decoder){
        this.decoder = decoder;
    }

    public AutomaticMovingCharacter makeAutomaticMover(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){
        CollisionChart chart = collisionChart(info.get(ImmutableInfo.COLLIDABLE_TYPE_KEY));

        MovementQueue queue = decoder.getMovementQueue(type);

        AutomaticMovingCharacter newMover;
        try {
            newMover = (AutomaticMovingCharacter) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class, MovementQueue.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info, queue);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return newMover;
    }

    public MainCharacter makeMainCharacter(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){
        CollisionChart chart = collisionChart(type);

        MainCharacter main;
        try {
            main = (MainCharacter) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return main;
    }

    public BasicStaticCharacter makeLivingStaticCharacter(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){
        CollisionChart chart = collisionChart(type);

        BasicStaticCharacter newStaticLiver;
        try {
            newStaticLiver = (BasicStaticCharacter) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return newStaticLiver;
    }



    public CollidableEntity makeCollidable(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info) {
        CollisionChart chart = collisionChart(type);

        CollidableEntity newCollidable;
        try {
            newCollidable = (CollidableEntity) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return newCollidable;
    }

    public GameEnderCollidableEntity makeGameEnder(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info) {
        CollisionChart chart = collisionChart(type);

        GameEnderCollidableEntity newGameEnder;
        try {
            newGameEnder = (GameEnderCollidableEntity) Class.forName(Model.entityClassResources.getString(type)).
                    getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
                    .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new InvalidTypeException("JSON holds invalid type",e);
        }

        return newGameEnder;
    }

    private CollisionChart collisionChart(String type){
        CollisionChartGetter collisionChartGetter = new DefaultCollisionChartGetter();
        return collisionChartGetter.getCollisionChart(decoder, type);
    }
}

