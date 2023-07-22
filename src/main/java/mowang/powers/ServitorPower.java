package mowang.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import mowang.Helpers.ModHelper;

public class ServitorPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = ModHelper.MakePath(ServitorPower.class.getSimpleName());
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String PATH128 = "ModExampleResources/img/powers/Example84.png";
    private static final String PATH48 = "ModExampleResources/img/powers/Example32.png";

    public ServitorPower(AbstractCreature owner, int amt) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = amt;

        // 添加一大一小两张能力图
        String path128 = PATH128;
        String path48 = PATH48;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void atStartOfTurn() {
        // 注意，owner不是玩家时触发
        if (!this.owner.isPlayer) {
            this.addToBot(new LoseHPAction(this.owner,AbstractDungeon.player,amount));
        }
    }

    @Override
    public int onLoseHp(int damageAmount) {
        if (this.owner.isPlayer){
            if (amount>=damageAmount){
                this.addToBot(new ReducePowerAction(owner,owner,POWER_ID,damageAmount));
                return 0;
            }else {
                this.addToBot(new RemoveSpecificPowerAction(owner,owner,POWER_ID));
                return damageAmount-amount;
            }
        }
        return damageAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}