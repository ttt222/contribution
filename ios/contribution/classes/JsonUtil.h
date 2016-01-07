//
//  JsonUtil.h
//  contribution
//
//  Created by 352 on 16/1/7.
//  Copyright © 2016年 352. All rights reserved.
//

#ifndef JsonUtil_h
#define JsonUtil_h


#endif /* JsonUtil_h */

#import <Foundation/Foundation.h>

@interface JsonUtil : NSObject

+ (NSString *)DictionaryToJsonString:(NSDictionary *)dictionary;
+ (NSMutableData *)DictionaryToMutableData:(NSDictionary *)dictionary;
+ (NSDictionary *)JsonStringToDictionary:(NSString *)string;

@end