## Description

In EntityFactory, some methods to create entities created collision charts based on the TYPE instead
of COLLIDABLE_TYPE in EntityInfo, which causes an error

## Expected Behavior

CollisionCharts are determined based on COLLIDABLE_TYPE key

## Current Behavior

CollisionCharts are determined based on TYPE key

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Create an object in level.json that has a type that's not in collions.json, and a COLLIDABLE_TYPE 
that is in collisions.json

## Failure Logs

ooga.controller.exceptions.CollisionChartCreationException: invalid_type
at ooga.controller.JSONInformationDecoder.makeCollisionDataFromJSONObject(JSONInformationDecoder.java:176) ~[classes/:?]

## Hypothesis for Fixing the Bug

use this:
info.get(ImmutableInfo.COLLIDABLE_TYPE_KEY)

instead of this:
type