/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.functions;

/**
 *
 * @author oscvic
 */
public interface Action {
	
	void invoke();
	
	public static interface $1<T1> extends Action{void invoke(T1 arg1);}
	public static interface $2<T1,T2> extends Action{void invoke(T1 arg1,T2 arg2);}
	public static interface $3<T1,T2,T3> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3);}
	public static interface $4<T1,T2,T3,T4> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3,T4 arg4);}
	public static interface $5<T1,T2,T3,T4,T5> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3,T4 arg4,T5 arg5);}
	public static interface $6<T1,T2,T3,T4,T5,T6> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3,T4 arg4,T5 arg5,T6 arg6);}
	public static interface $7<T1,T2,T3,T4,T5,T6,T7> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3,T4 arg4,T5 arg5,T6 arg6,T7 arg7);}
	public static interface $8<T1,T2,T3,T4,T5,T6,T7,T8> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3,T4 arg4,T5 arg5,T6 arg6,T7 arg7,T8 arg8);}
	public static interface $9<T1,T2,T3,T4,T5,T6,T7,T8,T9> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3,T4 arg4,T5 arg5,T6 arg6,T7 arg7,T8 arg8,T9 arg9);}
	public static interface $0<T1,T2,T3,T4,T5,T6,T7,T8,T9,T0> extends Action{void invoke(T1 arg1,T2 arg2,T3 arg3,T4 arg4,T5 arg5,T6 arg6,T7 arg7,T8 arg8,T9 arg9,T0 arg0);}
	public static interface $A<T> extends Action{void invoke(@SuppressWarnings("unchecked") T...args);}
}
