package gdx.diablo.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

import gdx.diablo.Diablo;
import gdx.diablo.codec.excel.Objects;
import gdx.diablo.graphics.PaletteIndexedBatch;
import gdx.diablo.map.DS1;
import gdx.diablo.map.DT1.Tile;

public class StaticEntity extends Entity {
  private static final String TAG = "StaticEntity";

  DS1.Object    object;
  Objects.Entry base;

  public StaticEntity(DS1.Object object, Objects.Entry base) {
    super(base.Token, EntType.OBJECT);
    this.object = object;
    this.base = base;
    setName(base.Name);
    init();
  }

  public static StaticEntity create(DS1 ds1, DS1.Object obj) {
    assert obj.type == DS1.Object.STATIC_TYPE;

    int id = Diablo.files.obj.getType2(ds1.getAct(), obj.id);
    Objects.Entry base = Diablo.files.objects.get(id);
    if (base == null) return null; // TODO: Which ones fall under this case?
    if (!base.Draw) return null; // TODO: Not yet
    return new StaticEntity(obj, base);
  }

  @Override
  protected void update() {
    super.update();
    int mode = Diablo.files.ObjMode.index(this.mode);
    //System.out.println(getName() + " " + this.mode + "(" + mode + ") " + object.FrameDelta[mode]);
    animation.setLooping(base.CycleAnim[mode]);
    animation.setFrame(base.Start[mode]);
    animation.setFrameDelta(base.FrameDelta[mode]); // FIXME: anim framedelta looks too quick
  }

  @Override
  public void drawLabel(PaletteIndexedBatch batch) {
    float x = +(position.x * Tile.SUBTILE_WIDTH50)  - (position.y * Tile.SUBTILE_WIDTH50);
    float y = -(position.x * Tile.SUBTILE_HEIGHT50) - (position.y * Tile.SUBTILE_HEIGHT50);
    label.setPosition(x, y - base.NameOffset + label.getHeight(), Align.center);
    label.draw(batch, 1);
  }

  @Override
  public boolean contains(Vector3 coords) {
    int mode = Diablo.files.ObjMode.index(this.mode);
    if (!base.Selectable[mode]) return false;
    return super.contains(coords);
  }

  private void init() {
    switch (base.InitFn) {
      case 0:
        break;
      case 1: case 2: case 3: case 4: case 5: case 6: case 7:
        break;
      case 8: // torch
        setMode("ON");

        // FIXME: Set random start frame?
        //int framesPerDir = animation.getNumFramesPerDir();
        //animation.setFrame(MathUtils.random(0, framesPerDir - 1));
        break;
      case 9 : case 10: case 11: case 12: case 13: case 14: case 15: case 16:
        break;
      case 17: // waypoint
        // TODO: Automatically sets on
        setMode("ON");
        break;
      case 18:
      case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28:
      case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38:
      case 39: case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48:
      case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58:
      case 59: case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68:
      case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78:
      case 79:
        break;
      default:
        Gdx.app.error(TAG, "Invalid InitFn for " + getName() + ": " + base.InitFn);
    }
  }

  private void operate() {
    switch (base.OperateFn) {
      case 0:
        break;
      case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
      case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
      case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29:
      case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
      case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49:
      case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59:
      case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69:
      case 70: case 71: case 72: case 73:
        break;
      default:
        Gdx.app.error(TAG, "Invalid OperateFn for " + getName() + ": " + base.OperateFn);
    }
  }

  private void populate() {
    switch (base.PopulateFn) {
      case 0:
        break;
      case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
        break;
      default:
        Gdx.app.error(TAG, "Invalid PopulateFn for " + getName() + ": " + base.PopulateFn);
    }
  }

  private void client() {
    switch (base.ClientFn) {
      case 0:
        break;
      case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
      case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18:
        break;
      default:
        Gdx.app.error(TAG, "Invalid ClientFn for " + getName() + ": " + base.ClientFn);
    }
  }
}