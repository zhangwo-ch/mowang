package mowang.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import mowang.ModCore.ExampleMod;
import mowang.Action.PlayCardAction;

public class SryActionPatch {
	@SpirePatch(clz = ScryAction.class, method = "update")
	public static class PolarDay {
		@SpireInsertPatch(rloc = 27)
		public static SpireReturn insert(ScryAction _inst) {
			for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
				//ExampleMod.logger.info(c.cardID);
				if(c.cardID.equals("mowang:PolarDay")) {
					AbstractDungeon.actionManager.addToBottom(new PlayCardAction(c.makeCopy()));
				}
			}
			return SpireReturn.Continue();
		}
	}
}
