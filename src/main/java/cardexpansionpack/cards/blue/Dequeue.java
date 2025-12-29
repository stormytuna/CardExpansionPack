package cardexpansionpack.cards.blue;


import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Dequeue extends BaseCard {
    public static final String ID = makeID("Dequeue");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    public Dequeue() {
        super(ID, INFO);
        setDamage(7, 3);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Dequeue();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));

        if (p.orbs.isEmpty()) {
            return;
        }

        for (int i = p.orbs.size() - 1; i >= 0; i--) {
            AbstractOrb orb = p.orbs.get(i);
            if (!(orb instanceof EmptyOrbSlot)) {
                addToBot(new EvokeSpecificOrbAction(orb));
                return;
            }
        }
    }
}

